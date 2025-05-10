package com.emo.haha.test

class DelegateGamePlayer(val gamer: ProfessionGamer):Gamer by gamer{
    override fun start() {
        gamer.start()
    }
}
