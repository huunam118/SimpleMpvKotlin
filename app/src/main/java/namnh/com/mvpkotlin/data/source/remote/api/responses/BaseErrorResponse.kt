package namnh.com.mvpkotlin.data.source.remote.api.responses

import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.Nullable
import com.google.gson.annotations.Expose

/**
 * Created by namnh on 02/05/2018.
 */
class BaseErrorResponse(@Expose val error: Error?) : Parcelable {
  constructor(parcel: Parcel) : this(parcel.readParcelable(Error::class.java.classLoader) as Error)

  override fun writeToParcel(dest: Parcel?, flags: Int) {
    dest?.writeParcelable(error, flags)
  }

  override fun describeContents(): Int {
    return 0
  }

  fun hasError(): Boolean = error != null
  fun getErrorCode(): Int = error?.code!!
  fun getErrorTitle(): String = error?.title!!
  fun getErrorMessage(): String = error?.message!!

  companion object CREATOR : Parcelable.Creator<BaseErrorResponse> {
    override fun createFromParcel(parcel: Parcel): BaseErrorResponse {
      return BaseErrorResponse(parcel)
    }

    override fun newArray(size: Int): Array<BaseErrorResponse?> {
      return arrayOfNulls(size)
    }
  }

  class Error constructor(@Expose val code: Int, @Nullable @Expose val title: String?, @Expose val message: String) :
    Parcelable {

    constructor(pc: Parcel) : this(pc.readInt(), pc.readString(), pc.readString())

    override fun writeToParcel(dest: Parcel?, flags: Int) {
      dest?.let {
        dest.writeInt(code)
        dest.writeString(title)
        dest.writeString(message)
      }
    }

    override fun describeContents(): Int {
      return 0
    }

    companion object CREATOR : Parcelable.Creator<Error> {
      override fun createFromParcel(parcel: Parcel): Error {
        return Error(parcel)
      }

      override fun newArray(size: Int): Array<Error?> {
        return arrayOfNulls(size)
      }
    }
  }
}
