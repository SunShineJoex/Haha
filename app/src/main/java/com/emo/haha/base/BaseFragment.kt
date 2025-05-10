package com.emo.haha.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.emo.haha.ext.singleClick
import java.lang.reflect.ParameterizedType

/**
 * @author: SunshineJoex
 * @e-mail: sunshinejoex@gmail.com
 * @date:   2025/5/9 15:26
 * @description:
 */
abstract class BaseFragment<VB : ViewBinding> : Fragment(), View.OnClickListener {

    protected lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = initBinding(inflater)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    protected abstract fun initView()
    protected abstract fun initData()

    protected fun initBinding(inflater: LayoutInflater): View? {
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val vbClazz = type.actualTypeArguments[1] as Class<*>
            vbClazz.getDeclaredMethod("inflate", LayoutInflater::class.java).let {
                binding = it.invoke(null, inflater) as VB
                return binding.root
            }
        }
        return null
    }


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

}