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

data class Movie(
    val title : String,
    val popularity : Double,
    val description : String,
    val openDate : String,
    val posterUrl : Int
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
            imgPoster.setImageResource(movie.posterUrl)
            tvTitle.text = movie.title
            tvPopularity.text = "인기도 : " + movie.popularity // 문자열 + 실수형으로 쓰면 문자열으로 자동 변환
            tvDescription.text = "설명 : " + movie.description
            tvOpenDate.text = "개봉일 : " + movie.openDate

            container.setOnClickListener { // 셀을 클릭했을 때 영화 제목을 토스트 메지로 띄움
                Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}