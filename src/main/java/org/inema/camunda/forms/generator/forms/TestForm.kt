package org.inema.camunda.forms.generator.forms

import org.inema.camunda.forms.generator.CamForm

object TestForm : CamForm({
    form(name = "HelloForm") {
        stringControl("hello")
        dateControl("date")
        integerControl("age")
    }
})