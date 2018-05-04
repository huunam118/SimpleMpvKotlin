package namnh.com.mvpkotlin.features.tasklist

import dagger.Component
import namnh.com.mvpkotlin.di.components.AppComponent
import namnh.com.mvpkotlin.di.scopes.FragmentScope

@Component(modules = [TaskListModule::class], dependencies = [AppComponent::class])
@FragmentScope
interface TaskListComponent {
  fun inject(fragment: TaskListFragment)
}