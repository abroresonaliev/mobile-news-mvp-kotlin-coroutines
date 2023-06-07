package uz.icebergsoft.mobilenews.presentation.presentation.readcollection.router

sealed class LaunchType {

    object LaunchForBreakingArticles : LaunchType()

    object LaunchForTopArticles : LaunchType()

    object LaunchForRecommendedArticles : LaunchType()

    object LaunchForReadLaterArticles : LaunchType()
}