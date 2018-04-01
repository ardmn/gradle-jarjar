package ru.tinkoff.gradle.jarjar.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @author Dmitriy Tarasov
 */
class JarJarTask extends DefaultTask {

    @TaskAction
    def taskAction() {
        if (!project.file('build/libs').exists()) {
            project.file('build/libs').mkdir()
        }

        project.ant {
            taskdef name: 'jarjar', classname: 'com.tonicsystems.jarjar.JarJarTask', classpath: this.project.configurations.jarJar.asPath
        }

        def rules = project.jarJar.rules
        rules.keySet().each { key ->
            if (!project.file("build/libs/${key}").exists()) {
                def ruleParts = rules[key].split(' ')
                project.ant {
                    jarjar(jarfile: "build/libs/${key}") {
                        zipfileset(src: "build/libs/${key}.original")
                        for(int i=0;i<ruleParts.length ;i+2) {
                            rule pattern: ruleParts[i], result: ruleParts[i+1]
                        }
                    }
                }
            }
        }

    }
}