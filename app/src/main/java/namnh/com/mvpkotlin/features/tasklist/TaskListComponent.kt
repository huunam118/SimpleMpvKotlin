package namnh.com.mvpkotlin.features.tasklist

import dagger.Component
import namnh.com.mvpkotlin.di.components.AppComponent
import namnh.com.mvpkotlin.di.scopes.ActivityScope

/**
 * Created by namnh on 03/05/2018.
 */
@Component(dependencies = [AppComponent::class], modules = [TaskListModule::class])
@ActivityScope
interface TaskListComponent {
  fun inject(activity: TaskListActivity)
}