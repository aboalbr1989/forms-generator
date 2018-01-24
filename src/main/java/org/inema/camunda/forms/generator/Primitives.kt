package org.inema.camunda.forms.generator

import kotlinx.html.*


abstract class AbstractFormControl(private val name: String) : FormControl {
    var cssClass: String = "col-md-12"
    var label: String = name
    var camVariable: Boolean = true
    var directives: MutableMap<String, String> = HashMap()
    var readOnly = false
    var required = false

    abstract val type: Pair<String, InputType>

    override fun html(): HtmlBlockTag.() -> Unit {
        return {
            div("form-group $cssClass") {
                label {
                    htmlFor = this@AbstractFormControl.name
                    text(this@AbstractFormControl.label)
                }
                input(classes = "form-control", type = this@AbstractFormControl.type.second) {
                    id = this@AbstractFormControl.name
                    required = this@AbstractFormControl.required
                    readonly = this@AbstractFormControl.readOnly
                    if (camVariable) {
                        attributes["cam-variable-name"] = this@AbstractFormControl.name
                        attributes["cam-variable-type"] = this@AbstractFormControl.type.first
                        attributes.putAll(directives)
                    }
                }
            }
        }
    }
}



class StringControl(name: String) : AbstractFormControl(name) {
    override val type: Pair<String, InputType> = Pair("String", InputType.text)
}

class IntegerControl(name: String) : AbstractFormControl(name) {
    override val type: Pair<String, InputType> = Pair("Integer", InputType.number)
}

class FloatControl(name: String) : AbstractFormControl(name) {
    override val type: Pair<String, InputType> = Pair("Float", InputType.number)
}

class DoubleControl(name: String) : AbstractFormControl(name) {
    override val type: Pair<String, InputType> = Pair("Double", InputType.number)
}

class LongControl(name: String) : AbstractFormControl(name) {
    override val type: Pair<String, InputType> = Pair("Long", InputType.number)
}

class DateControl(name: String) : AbstractFormControl(name) {
    override val type: Pair<String, InputType> = Pair("String", InputType.date)
}

class FileControl(name: String) : AbstractFormControl(name) {
    var maxFileSize: String?
        get() = directives["cam-max-filesize"]
        set(value) {
            directives.put("cam-max-filesize", value!!)
        }
    override val type: Pair<String, InputType> = Pair("File", InputType.file)
}

class DownloadButton(private val name: String) : FormControl {
    var text: String = ""
    override fun html(): HtmlBlockTag.() -> Unit {
        return {
            div(classes = "col-lg-12") {
                a(classes = "btn btn-success") {
                    attributes["cam-file-download"] = this@DownloadButton.name
                    i(classes = "glyphicon glyphicon-download") {
                        text(this@DownloadButton.text)
                    }
                }
            }
        }
    }

}