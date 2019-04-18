package example.micronaut

import groovy.transform.CompileStatic
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

/**
 *  The type Generate build info task.
 */
@CompileStatic
class GenerateBuildInfoTask extends DefaultTask {

    public static final String KEY_NAME = "name"
    public static final String KEY_VERSION = "version"
    @Input
    String versionPropertyName

    @Input
    String projectName

    @OutputFile
    File outputFile

    @InputFile
    File inputFile

    @TaskAction
    def generate() {
        Properties props = new Properties()
        props.load(inputFile.newDataInputStream())
        Properties outputProps = new Properties()
        outputProps.setProperty(KEY_VERSION, props.get(versionPropertyName) as String)
        outputProps.setProperty(KEY_NAME, projectName)
        outputProps.store(outputFile.newOutputStream(), "")
    }
}