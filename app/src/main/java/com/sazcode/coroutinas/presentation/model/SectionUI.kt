package com.sazcode.coroutinas.presentation.model

data class SectionUI (
    val id: String,
    val isLoading: Boolean = false,
    val title: String = "",
    val items: List<MixedContentUI> = emptyList(),
    val error: String? = null
)
