package com.study.blog

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val don = User("sbd530", "Byungdon", "Yoon")
        entityManager.persist(don)
        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", don)
        entityManager.persist(article)
        entityManager.flush()
        //!! = NotNull, ? = Nullable
        val findArticle = articleRepository.findByIdOrNull(article.id!!)
        assertThat(findArticle).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`() {
        val don = User("sbd530", "Byungdon", "Yoon")
        entityManager.persist(don)
        entityManager.flush()
        val user = userRepository.findByLogin(don.login)
        assertThat(user).isEqualTo(don)
    }
}