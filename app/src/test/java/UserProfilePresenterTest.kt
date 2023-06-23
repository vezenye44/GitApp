import com.example.gitapp.domain.dto.UserProfileDTO
import com.example.gitapp.domain.repo.UserProfileRepo
import com.example.gitapp.ui.user.UserProfileContract.View
import com.example.gitapp.ui.user.UserProfilePresenter
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserProfilePresenterTest {

    private lateinit var presenter: UserProfilePresenter

    /** Фейковое значение с сервера */
    private val fakeUserProfileDTO = UserProfileDTO("user", 1, "")
    /** Фейковая ошибка с сервера */
    private val fakeThrowable = Throwable("fake throwable")
    /** Репозиторий всегда возвращающий заданное значение с сервера */
    private val onlyErrorUserProfileRepo = object : UserProfileRepo {
        override fun getUserProfile(
            callbackSuccess: (UserProfileDTO) -> Unit,
            callbackError: ((Throwable) -> Unit)?,
        ) {
            callbackError?.invoke(fakeThrowable)
        }
    }
    /** Репозиторий всегда возвращающий заданную ошибку с сервера */
    private val onlySuccessUserProfileRepo = object : UserProfileRepo {
        override fun getUserProfile(
            callbackSuccess: (UserProfileDTO) -> Unit,
            callbackError: ((Throwable) -> Unit)?,
        ) {
            callbackSuccess(fakeUserProfileDTO)
        }
    }

    @Mock
    private lateinit var userProfileRepo: UserProfileRepo

    @Before
    fun singUp() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun userProfilePresenterTest_attach_showLoadingProcess_false() {
        val view = Mockito.mock(View::class.java)

        presenter = UserProfilePresenter(userProfileRepo)

        presenter.attach(view)

        verify(view, times(1)).showLoadingProcess(false)
    }

    @Test
    fun userProfilePresenterTest_attachAfterSuccessLoadData_showSuccess() {
        val firstAttachedView = Mockito.mock(View::class.java)
        val secondAttachedView = Mockito.mock(View::class.java)

        presenter = UserProfilePresenter(onlySuccessUserProfileRepo)

        presenter.attach(firstAttachedView)
        presenter.attach(secondAttachedView)

        verify(firstAttachedView, times(1)).showData(fakeUserProfileDTO)
        verify(secondAttachedView, times(1)).showData(fakeUserProfileDTO)
    }

    @Test
    fun userProfilePresenterTest_attachAfterErrorLoadData_notShowError() {
        val firstAttachedView = Mockito.mock(View::class.java)
        val secondAttachedView = Mockito.mock(View::class.java)

        presenter = UserProfilePresenter(onlyErrorUserProfileRepo)

        presenter.attach(firstAttachedView)
        presenter.attach(secondAttachedView)

        verify(firstAttachedView, times(1)).showError(fakeThrowable)
        verify(secondAttachedView, times(0)).showError(fakeThrowable)
    }

    @Test
    fun userProfilePresenterTest_loadData_showLoadingProcess_true() {
        val view = Mockito.mock(View::class.java)

        presenter = UserProfilePresenter(userProfileRepo)

        presenter.attach(view)

        verify(view, times(1)).showLoadingProcess(true)
    }

    @Test
    fun userProfilePresenterTest_loadData_Success() {
        val view = Mockito.mock(View::class.java)

        presenter = UserProfilePresenter(onlySuccessUserProfileRepo)

        presenter.attach(view)

        verify(view, times(1)).showData(fakeUserProfileDTO)
    }

    @Test
    fun userProfilePresenterTest_loadData_Error() {
        val view = Mockito.mock(View::class.java)

        presenter = UserProfilePresenter(onlyErrorUserProfileRepo)

        presenter.attach(view)

        verify(view, times(1)).showError(fakeThrowable)
    }
}