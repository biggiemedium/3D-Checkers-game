package dev.px.Game.Logic

import dev.px.Game.Logic.CheckerColor
import dev.px.Game.Logic.CheckerState

class Checker {

    var state = CheckerState.Normal
    var color = CheckerColor.WHITE

    constructor(color: CheckerColor, state: CheckerState) {
        this.color = color
        this.state = state
    }

}