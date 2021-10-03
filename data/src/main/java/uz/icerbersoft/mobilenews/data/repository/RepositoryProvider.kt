package uz.icerbersoft.mobilenews.data.repository

import uz.icerbersoft.mobilenews.data.repository.article.ArticleRepository

interface RepositoryProvider {

    val articleRepository: ArticleRepository
}