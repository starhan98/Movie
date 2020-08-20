package com.example.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

// GSON은 JSON 데이터의 변수의 이름과 data class의 변수의 이름을 각각 비교해서, 이름이 똑같은 값이 있으면 알맞게 넣어준다.

// 영화 정보를 담고있는 data class
// JSON의 변수명과 Movie의 변수명이 일치해야 한다.
data class Movie(
    val title : String,
    val popularity : Double,
    val overview : String,
    val release_date : String,
    val poster_path : String
)

// ArrayList에 대한 data class
// JSON과 일치시켜줘야하므로 변수명은 results가 된다.
data class MovieList(
    val results: ArrayList<Movie>
)

class MovieAdapter(val context: Context, val movieList: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 셀 레이아웃을 불러오는 역할.
        val view = LayoutInflater.from(context).inflate(R.layout.cell, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        // 셀 개수를 설정하는 역할. 셀 개수를 반환하는 함수.
        return movieList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 각 셀에 맞는 정보를 넣는 역할.
        holder.bind(movieList[position])
    }

    //셀.
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 셀의 구성요소를 불러오는 역할.
        val imgPoster = itemView.findViewById<ImageView>(R.id.imgPoster)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvPopularity = itemView.findViewById<TextView>(R.id.tvPopularity)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val tvOpenDate = itemView.findViewById<TextView>(R.id.tvOpenDate)
        val container = itemView.findViewById<ConstraintLayout>(R.id.container) // 셀을 둘러쌓는 부분

        // 데이터를 셀에 넣는 역할.
        fun bind(movie: Movie){
            val overview: String
            if(movie.overview.length > 21) {
                overview = movie.overview.slice(IntRange(0, 20)) + "..."
            }
            else{
                overview = movie.overview
            }

            Glide.with(context).load("https://image.tmdb.org/t/p/w500" + movie.poster_path).into(imgPoster)
            tvTitle.text = movie.title
            tvPopularity.text = "인기도 : " + movie.popularity // 문자열 + 실수형으로 쓰면 문자열으로 자동 변환
            tvDescription.text = "설명 : " + overview
            tvOpenDate.text = "개봉일 : " + movie.release_date

            container.setOnClickListener { // 셀을 클릭했을 때 영화 제목을 토스트 메지로 띄움
                Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}