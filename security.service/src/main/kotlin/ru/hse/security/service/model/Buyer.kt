package ru.hse.security.service.model

import ru.hse.security.service.model.Buyer.Companion.TABLE_NAME
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.util.*

@Table(TABLE_NAME)
data class Buyer(
    @Id
    @Column(ID_COLUMN_NAME)
    val id: UUID? = null,

    @Column(USERNAME_COLUMN_NAME)
    private var username: String,

    @Column(EMAIL_COLUMN_NAME)
    private val email: String,

    @Column(PASSWORD_COLUMN_NAME)
    private var password: String,

    @Column(CREATED_COLUMN_NAME)
    private val created: LocalDateTime? = null
) : UserDetails {
    fun setName(name: String) {
        this.username = name
    }

    override fun getUsername() = username

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun getPassword() = password

    fun getEmail() = email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

    companion object {
        const val TABLE_NAME = "user_table"

        const val ID_COLUMN_NAME = "id"
        const val USERNAME_COLUMN_NAME = "username"
        const val EMAIL_COLUMN_NAME = "email"
        const val PASSWORD_COLUMN_NAME = "password"
        const val CREATED_COLUMN_NAME = "created"
    }
}