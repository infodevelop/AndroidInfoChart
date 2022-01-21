package com.infomining.infochart_demo.activity

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import com.infomining.infochart_demo.DummyData
import com.infomining.infochart_demo.R
import com.infomining.infochartlib.chart.RealTimeVitalChart
import com.infomining.infochartlib.data.Spec

class RealTimeChartActivity : BaseActivity() {

    lateinit var chart: RealTimeVitalChart
    lateinit var spec: Spec

    lateinit var btnStart: TextView
    lateinit var btnStop: TextView
    lateinit var btnAdd: TextView
    lateinit var btnReset: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_time_chart)
        title = "Realtime Vital Chart"

        initView()
    }

    private fun initView() {
        chart = findViewById(R.id.chart_vital)
        btnStart = findViewById(R.id.btn_start)
        btnStop = findViewById(R.id.btn_stop)
        btnAdd = findViewById(R.id.btn_add)
        btnReset = findViewById(R.id.btn_reset)

        btnStart.setOnClickListener {
            chart.dataHandler.run()
        }

        btnStop.setOnClickListener {
            chart.dataHandler.stop()
        }

        btnAdd.setOnClickListener {
            for (i in DummyData.ecgDummy1) {
                chart.dataHandler.enqueue(i.toFloat())
            }
        }

        btnReset.setOnClickListener {
            chart.reset()
        }

        initChart()
    }

    private fun initChart() {
        spec = Spec()
        chart.setRealTimeSpec(spec)
    }

    override fun onDestroy() {
        super.onDestroy()
        chart.dataHandler.destroy()
    }


}