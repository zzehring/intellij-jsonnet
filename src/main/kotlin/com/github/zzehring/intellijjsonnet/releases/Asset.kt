package com.github.zzehring.intellijjsonnet.releases

import kotlinx.serialization.Serializable

@Suppress("PROVIDED_RUNTIME_TOO_LOW")
@Serializable
data class Asset(
    val browser_download_url: String,
    val content_type: String,
    val created_at: String,
    val download_count: Int,
    val id: Int,
    val label: String,
    val name: String,
    val node_id: String,
    val size: Int,
    val state: String,
    val updated_at: String,
    val uploader: Uploader,
    val url: String
)