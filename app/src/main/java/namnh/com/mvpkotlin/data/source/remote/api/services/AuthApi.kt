package namnh.com.mvpkotlin.data.source.remote.api.services

import io.reactivex.Observable
import namnh.com.mvpkotlin.data.model.Task
import namnh.com.mvpkotlin.data.source.remote.api.requests.CreateTaskRequest
import retrofit2.http.*

/**
 * Created by namnh on 02/05/2018.
 */
interface AuthApi {
  @GET("api/task/owner")
  fun getTasks(@Query("uid") uid: String): Observable<List<Task>>

  @POST("api/task/create")
  fun createTask(@Body request: CreateTaskRequest): Observable<Task>

  @PUT("api/task/update")
  @FormUrlEncoded
  fun updateTaskStatus(@Path("uid") uid: String, @Path(
      "status") status: String): Observable<Task>
}