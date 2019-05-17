package com.example.rxjavasamples.subjects

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rxjavasamples.R

class SubjectsActivity : AppCompatActivity() {

    private val tag : String = SubjectsActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subjects)
        //firstDemo()
        //secondDemo()
        //behaviourSubject()
        //behaviourSubject2()
        //publishSubject()
        //publishSubject2()
        //replaySubjectDemo()
        replaySubjectDemo2()
    }

    private fun replaySubjectDemo2() {
        BehaviourSubjectDemo().replaySubjectDemo2()

    }

    private fun replaySubjectDemo() {
        BehaviourSubjectDemo().replaySubjectDemo()
    }

    private fun publishSubject2() {
        BehaviourSubjectDemo().publishSubjectDemo2()

    }

    private fun publishSubject() {
        BehaviourSubjectDemo().publishSubjectDemo()
    }

    private fun behaviourSubject() {
        BehaviourSubjectDemo().behaviourSubjectDemo()
    }

    private fun behaviourSubject2() {
        BehaviourSubjectDemo().behaviourSubjectDemo2()
    }

    private fun secondDemo() {
        SecondDemo().asyncSubjectDemo2()
    }

    private fun firstDemo() {
        FirstDemo().asyncSubjectDemo1()
    }

}
