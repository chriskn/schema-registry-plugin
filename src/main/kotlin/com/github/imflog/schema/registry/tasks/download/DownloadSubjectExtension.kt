package com.github.imflog.schema.registry.tasks.download

import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

open class DownloadSubjectExtension(objects: ObjectFactory) {

    companion object {
        const val EXTENSION_NAME = "download"
    }

    val metadata: Property<MetadataExtension> = objects.property(MetadataExtension::class.java).apply {
        convention(MetadataExtension(false, null))
    }

    val subjects: ListProperty<DownloadSubject> = objects.listProperty(DownloadSubject::class.java).apply {
        convention(listOf())
    }

    fun subject(inputSubject: String, outputPath: String) {
        subjects.add(DownloadSubject(inputSubject, outputPath))
    }

    fun subject(inputSubject: String, outputPath: String, outputFileName: String) {
        subjects.add(DownloadSubject(inputSubject, outputPath, outputFileName = outputFileName))
    }

    fun subject(inputSubject: String, outputPath: String, version: Int) {
        subjects.add(DownloadSubject(inputSubject, outputPath, version))
    }

    fun subject(inputSubject: String, outputPath: String, version: Int, outputFileName: String) {
        subjects.add(DownloadSubject(inputSubject, outputPath, version, outputFileName = outputFileName))
    }

    fun subjectPattern(inputPattern: String, outputPath: String) {
        subjects.add(DownloadSubject(inputPattern, outputPath, null, true))
    }
}

data class DownloadSubject(
    val subject: String,
    val outputPath: String,
    val version: Int? = null,
    val regex: Boolean = false,
    val outputFileName: String? = null
)

data class MetadataExtension(
    val enabled: Boolean = false,
    val outputPath: String? = null
) {
    constructor(enabled: Boolean) : this(enabled, null)
}