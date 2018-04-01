package ru.tinkoff.gradle.jarjar

/**
 * @author Dmitriy Tarasov
 */
class JarJarPluginExtension {

    String jarJarDependency = 'com.ardmn:jarjar:1.0.14'

    Map<String, String> rules = [:]

}