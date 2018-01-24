package org.inema.camunda.forms.generator

import kotlinx.html.*

interface FormControl {
    fun html(): HtmlBlockTag.() -> Unit
}




