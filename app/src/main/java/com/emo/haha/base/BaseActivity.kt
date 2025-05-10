package com.emo.haha.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.emo.haha.ext.singleClick
import java.lang.reflect.ParameterizedType

/**
 * @author: SunshineJoex
 * @e-mail: sunshinejoex@gmail.com
 * @date:   2025/5/9 14:34
 * @description:
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity(), View.OnClickListener {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initView()
        initData()
    }

    protected abstract fun initView()
    protected abstract fun initData()

    /**
     * 注册点击事件
     */
    open fun registerClicks(vararg args: View?) {
        args.forEach {
            it?.setOnClickListener(this)
        }
    }

    override fun onClick(view: View?) {
        view?.singleClick {
            clickView(view)
        }
    }

    open fun clickView(view: View?) {

    }

    private fun initBinding() {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val vbClazz = type.actualTypeArguments[0] as Class<*>
            vbClazz.getDeclaredMethod("inflate", LayoutInflater::class.java).let {
                binding = it.invoke("base", layoutInflater) as VB
                setContentView(binding.root)
            }
        }
    }

}