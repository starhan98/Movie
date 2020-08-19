package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_movie_list.*

class MovieListActivity : AppCompatActivity() {

    val movieList : ArrayList<Movie> = arrayListOf(
        Movie("테넷", 22.433, "시간의 흐름은...", "2020-08-26", R.drawable.m2),
        Movie("소년 시절의 너", 17.321, "넌 세상을 지켜,...", "2020-07-09", R.drawable.m4),
        Movie("덩케르크 이스케이프", 15.842, "역사에 기록되 않은...", "2020-08-03", R.drawable.m3),
        Movie("짱구는 못말려: 신혼여행 허리케인", 10.954, "짱구 THE Movie...", "2020-08-07", R.drawable.m1)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

       val adapter = MovieAdapter(this, movieList) // Adapter 선언
        movieRecycler.adapter = adapter // RecyclerView에 우리가 만든 MovieAdapter 셋팅

        val lm = LinearLayoutManager(this) // LinearLayoutManager 선언 - RecyclerView의 방향을 결정함 (기본이 위에서 아래, 수평도 가능)
        movieRecycler.layoutManager = lm // RecyclerView에 LinearLayoutManager 선
    }
}