package com.talkingdata.maven.plugins.birdie;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talkingdata.maven.plugins.birdie.constant.Setting;
import com.talkingdata.maven.plugins.birdie.utils.LoggerUtil;
import org.apache.maven.model.Build;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;


/**
 * This is an entrance class of Birdie, which can be a bridge between developers and steaming 
 * framework, not only can encapsulate lower-level`s streaming framework,such as flink,spark-streaming
 * but also support a web UI page to configure operators and streaming work-flow.
 * 
 * Mojo as a maven-plugin is able to assign value to variable via annotation @parameter in java doc dynamically.
 * 
 * Goal which touches a timestamp file.
 *
 * @goal birdie
 * @phase process-sources
 * 
 * @author jian.wang
 */
public class BirdieMojo extends AbstractMojo {

    private static ObjectMapper jsonMapper = new ObjectMapper();
    /**
     * @parameter expression="${project}"
     * @readonly
     */
    private MavenProject project;

    /**
     * @parameter default-value="test" property="packageName"
     */
    private String packageName;

    /**
     * @parameter default-value="flink" property="engine"
     */
    private String engine;

    public void execute()
            throws MojoExecutionException {
        //初始化log
        LoggerUtil.setLog(getLog());

        //获取配置
        Build build = project.getBuild();
        Setting.engine = engine;
        Setting.packageName = packageName;
        Setting.outPath = build.getSourceDirectory() + "/" + packageName.replace(".", "/");
        String birdieFile = build.getSourceDirectory().replace("java", "resources") + "/birdie.json";

        LoggerUtil.getLog().info("birdie-maven-plugin >>> engine:" + engine);
        LoggerUtil.getLog().info("birdie-maven-plugin >>> packageName:" + packageName);
        LoggerUtil.getLog().info("birdie-maven-plugin >>> outPath:" + Setting.outPath);
        LoggerUtil.getLog().info("birdie-maven-plugin >>> birdieFile:" + birdieFile);

        //生成java代码
        JsonNode jsonObject = null;
        try {
            jsonObject = jsonMapper.readTree(new File(birdieFile));
            LoggerUtil.getLog().info("birdie-maven-plugin >>> " + jsonObject.toString());
        } catch (IOException e) {
            LoggerUtil.getLog().error(e.getMessage(), e);
        }
        // 生成算子
        OperatorGenerate.start(jsonObject.get("operator"));
        FlowGenerate.start(jsonObject.get("flow"));
    }
}
