package org.inema.camunda.forms.generator

import kotlinx.html.HtmlBlockTag
import kotlinx.html.form
import kotlinx.html.script
import kotlinx.html.stream.createHTML
import kotlinx.html.unsafe

open class CamForm(init: CamForm.() -> String) {

    var name: String = ""
    private val controls: MutableMap<String, FormControl> = LinkedHashMap()
    val form: String = this.run(init)

    private fun putControl(name: String, control: FormControl) {
        if (controls.containsKey(name)) {
            throw IllegalArgumentException("Form already contains control with name $name")
        }
        controls.put(name, control)
    }

    fun HtmlBlockTag.stringControl(name: String, fn: StringControl.() -> Unit = {}) {
        val control = StringControl(name)
        putControl(name, control)
        control.apply(fn)
        val func = control.html()
        this.apply {
            func()
        }
    }

    fun HtmlBlockTag.integerControl(name: String, fn: IntegerControl.() -> Unit = {}) {
        val control = IntegerControl(name)
        putControl(name, control)
        control.apply(fn)
        val func = control.html()
        this.apply {
            func()
        }
    }

    fun HtmlBlockTag.floatControl(name: String, fn: FloatControl.() -> Unit = {}) {
        val control = FloatControl(name)
        putControl(name, control)
        control.apply(fn)
        val func = control.html()
        this.apply {
            func()
        }
    }

    fun HtmlBlockTag.doubleControl(name: String, fn: DoubleControl.() -> Unit = {}) {
        val control = DoubleControl(name)
        putControl(name, control)
        control.apply(fn)
        val func = control.html()
        this.apply {
            func()
        }
    }

    fun HtmlBlockTag.longControl(name: String, fn: LongControl.() -> Unit = {}) {
        val control = LongControl(name)
        putControl(name, control)
        control.apply(fn)
        val func = control.html()
        this.apply {
            func()
        }
    }

    fun HtmlBlockTag.dateControl(name: String, fn: DateControl.() -> Unit = {}) {
        val control = DateControl(name)
        putControl(name, control)
        control.apply(fn)
        val func = control.html()
        this.apply {
            func()
        }
    }

    fun HtmlBlockTag.fileControl(name: String, fn: FileControl.() -> Unit = {}) {
        val control = FileControl(name)
        putControl(name, control)
        control.apply(fn)
        val func = control.html()
        this.apply {
            func()
        }
    }

    fun HtmlBlockTag.downloadButton(name: String, fn: DownloadButton.() -> Unit = {}) {
        val control = DownloadButton(name)
        putControl(name, control)
        control.apply(fn)
        val func = control.html()
        this.apply {
            func()
        }
    }

    fun form(name: String, fn: HtmlBlockTag.() -> Unit): String {
        this.name = name
        return createHTML(prettyPrint = true).form {
            attributes["name"] = name
            fn()
            script(type = "text/form-script") {
                attributes["cam-script"] = ""
                unsafe {
                    raw("")
                }
            }
        }
    }
}