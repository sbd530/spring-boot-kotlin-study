package com.study.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {
        val don = userRepository.save(User("sbd530", "Byungdon", "Yoon"))
        articleRepository.save(Article(
                title = "title01",
                headline = "headline01",
                content = "content01",
                author = don
        ))
        articleRepository.save(Article(
                title = "title02",
                headline = "headline02",
                content = "content02",
                author = don
        ))
    }

}