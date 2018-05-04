package namnh.com.mvpkotlin.features

/**
 * Created by namnh on 03/05/2018.
 */
interface BasePresenter<T> {
  /**
   * Binds presenter with a view when onResume Fragment. The Presenter will perform initialization here.
   *
   * @param view the view associated with this presenter
   */
  fun takeView(view: T)

  /**
   * Drops the reference to the view when destroyed
   */
  fun dropView()
}