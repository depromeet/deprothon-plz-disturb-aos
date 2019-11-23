package com.depromeet.plzdisturb.makeroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.depromeet.plzdisturb.R
import com.depromeet.plzdisturb.data.dto.CreateRoomRequest
import com.depromeet.plzdisturb.data.dto.LoginResponse
import com.depromeet.plzdisturb.data.dto.RoomInfoResponse
import com.depromeet.plzdisturb.network.DisturbingService
import com.depromeet.plzdisturb.room.RoomActivity
import com.depromeet.plzdisturb.updateAccessToken
import kotlinx.android.synthetic.main.activity_make_room.*
import kotlinx.android.synthetic.main.activity_share.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random

class MakeRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_room)

        btn_make_room.setOnClickListener {
            Log.e(TAG, "asdfasfasdfasdf")
            DisturbingService.getApi().createRoom(CreateRoomRequest(Random(10000).nextInt()))
                .enqueue(object : Callback<RoomInfoResponse> {
                    override fun onFailure(call: Call<RoomInfoResponse>, t: Throwable) {
                        // Todo 실패 안할꺼야!!!
                    }

                    override fun onResponse(
                        call: Call<RoomInfoResponse>,
                        response: Response<RoomInfoResponse>
                    ) {
                        response.body()?.let {
                            Log.e(TAG, "asfasdfasdf ${it.room}")

                            val intent = Intent(this@MakeRoomActivity, RoomActivity::class.java)
                                .putExtra("KEY_CODE", it.room.code)
                            startActivity(intent)
                        }
                    }

                })
        }
    }

    companion object {
        private const val TAG = "MakeRoomActicity"
    }
}
