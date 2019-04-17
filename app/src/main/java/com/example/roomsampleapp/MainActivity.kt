package com.example.roomsampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import org.reactivestreams.Subscriber

class MainActivity : AppCompatActivity() {

    // データベース。
    private val taskDatabase = TaskRoomDatabase.getDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storeButton.setOnClickListener {
            onStoreButtonClicked()
        }
    }

    /**
     * ボタンタップを知らせる。
     */
    private fun onStoreButtonClicked() {
        val test = Observable.just("Press")
            .subscribe(getObserver())
    }

    private fun getObserver() = object : Observer<String> {
        /**
         * Subscribeにセットされた際に呼ばれる？
         */
        override fun onSubscribe(d: Disposable) {
            Log.d("test__", "subscribed")
        }

        /**
         * 完了した際に呼ばれる。
         */
        override fun onComplete() {
            Log.d("test__", "onComplete")
        }

        /**
         * 次の処理が呼ばれる。
         */
        override fun onNext(s: String) {
            Log.d("test__", "onNext -> $s")
            Observable.just(s).subscribe(createTask(s))
        }

        /**
         * エラー時に呼ばれる。
         */
        override fun onError(e: Throwable) {
            Log.d("test__", "onError")
        }
    }

}
