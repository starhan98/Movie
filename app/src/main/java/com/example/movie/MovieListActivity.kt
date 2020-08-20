package com.example.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.json.JSONException
import com.android.volley.toolbox.JsonObjectRequest as JsonObjectRequest

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        // Volley의 RequestQueue 선언
        var requestQueue: RequestQueue = Volley.newRequestQueue(this)

        // GSON 객체 선언
        var gson: Gson = Gson()

        // API 주소 선언.
        val url = "https://api.themoviedb.org/3/movie/now_playing?" + "api_key=f3c242ff274a50ec9b2ccb6c033fe7ec" + "&language=ko-KR" + "&region=KR"

        // API를 호출함.
        val request = JsonObjectRequest(Request.Method.GET, url, null,
        Response.Listener { // 데이터가 정상적으로 호출됐을 때 처리하는 부분.
            response -> try { // response(영화 JSON 데이터)가 정상적으로 넘어온 경우.
                // response(영화 JSON 데이터) -> MovieList data class로 변형. (by GSON)
                val data = gson.fromJson<MovieList>(response.toString(), MovieList::class.java)

                val adapter = MovieAdapter(this, data.results) // Adapter 선언
                movieRecycler.adapter = adapter // RecyclerView에 우리가 만든 MovieAdapter 셋팅

                val lm = LinearLayoutManager(this) // LinearLayoutManager 선언 - RecyclerView의 방향을 결정함 (기본이 위에서 아래, 수평도 가능)
                movieRecycler.layoutManager = lm // RecyclerView에 LinearLayoutManager 선언
            } catch (e: JSONException) { // response가 정상적으로 넘어오지 않은 경우.
                // 오류 내용을 Toast로 출력
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }, Response.ErrorListener { // 데이터가 정상적으로 호출되지 않았을 때 처리하는 부분.
            // 오류 내용을 Toast로 출력
            error -> Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
        })

        requestQueue.add(request)

    }
}