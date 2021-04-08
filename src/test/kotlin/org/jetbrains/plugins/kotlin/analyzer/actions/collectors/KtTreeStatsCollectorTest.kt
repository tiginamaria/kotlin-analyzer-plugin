package org.jetbrains.plugins.kotlin.analyzer.actions.collectors

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.testFramework.LightPlatformTestCase
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import com.intellij.testFramework.fixtures.impl.LightTempDirTestFixtureImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.nio.file.Paths

/**
 * Test recursive statistics collection by [KtTreeStatsCollector].
 */
@RunWith(Parameterized::class)
class KtTreeStatsCollectorTest(
    private val testClass: String,
    private val statistics: Map<String, Int?>
) : BasePlatformTestCase() {

    private lateinit var statsStorage: StatsStorage
    private lateinit var ktTreeStatsCollector: KtTreeStatsCollector
    private lateinit var myFixture: CodeInsightTestFixture

    private fun setUpFixture() {
        val factory = IdeaTestFixtureFactory.getFixtureFactory()
        val fixtureBuilder = factory.createLightFixtureBuilder()
        val fixture = fixtureBuilder.fixture
        myFixture = IdeaTestFixtureFactory.getFixtureFactory()
            .createCodeInsightFixture(fixture, LightTempDirTestFixtureImpl(true))
        myFixture.testDataPath = testDataPath
        myFixture.setUp()
    }

    private fun cleanUpFixture() {
        myFixture.tearDown()

        ApplicationManager.getApplication().invokeAndWait {
            LightPlatformTestCase.closeAndDeleteProject()
        }
    }

    private fun setUpCollector() {
        statsStorage = StatsStorage()
        ktTreeStatsCollector = KtTreeStatsCollector(statsStorage)
    }

    override fun getTestDataPath(): String {
        return Paths.get(KtTreeStatsCollectorTest::class.java.getResource("/testData").toURI()).toString()
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "testClass: {0}")
        fun data(): Iterable<Array<Any>> {
            return listOf(
                arrayOf(
                    "EmptyClass.kt",
                    mapOf(
                        "PACKAGE_DIRECTIVE" to 1,
                        "CLASS" to 1,
                        "PRIMARY_CONSTRUCTOR" to null
                    )
                ),
                arrayOf(
                    "TwoClasses.kt",
                    mapOf(
                        "PACKAGE_DIRECTIVE" to 1,
                        "REFERENCE_EXPRESSION" to 4,
                        "CLASS" to 2,
                        "PRIMARY_CONSTRUCTOR" to 2,
                        "VALUE_PARAMETER_LIST" to 2,
                        "VALUE_PARAMETER" to 3,
                        "TYPE_REFERENCE" to 3,
                        "USER_TYPE" to 3
                    )
                ),
                arrayOf(
                    "AbstractClass.kt",
                    mapOf(
                        "CLASS" to 1,
                        "STRING_TEMPLATE" to 2,
                        "LITERAL_STRING_TEMPLATE_ENTRY" to 1,
                        "WHILE" to 1,
                        "CONDITION" to 1,
                        "DOT_QUALIFIED_EXPRESSION" to 1,
                        "CALL_EXPRESSION" to 1,
                        "OPERATION_REFERENCE" to 2,
                        "PROPERTY" to 1
                    )
                ),
                arrayOf(
                    "GenericClass.kt",
                    mapOf(
                        "CLASS" to 1,
                        "TYPE_PROJECTION" to 1,
                        "CALL_EXPRESSION" to 1,
                    )
                ),
                arrayOf(
                    "InterfaceClass.kt",
                    mapOf(
                        "CLASS" to 1,
                        "FUN" to 2,
                        "BLOCK" to 1,
                    )
                ),
                arrayOf(
                    "LambdaClass.kt",
                    mapOf(
                        "CLASS" to 1,
                        "FUN" to 1,
                        "BLOCK" to 3,
                        "RETURN" to 1,
                        "DOT_QUALIFIED_EXPRESSION" to 2,
                        "CALL_EXPRESSION" to 3,
                        "LAMBDA_ARGUMENT" to 2,
                        "LAMBDA_EXPRESSION" to 2,
                        "FUNCTION_LITERAL" to 2,
                        "BINARY_EXPRESSION" to 2,
                        "OPERATION_REFERENCE" to 2,
                        "INTEGER_CONSTANT" to 1,
                    )
                ),
                arrayOf(
                    "OpenClass.kt",
                    mapOf(
                        "CLASS" to 2,
                        "MODIFIER_LIST" to 6,
                        "IF" to 2,
                        "CONDITION" to 2,
                        "THEN" to 2,
                        "ELSE" to 2,
                        "SUPER_TYPE_LIST" to 1,
                        "SUPER_TYPE_CALL_ENTRY" to 1,
                        "CONSTRUCTOR_CALLEE" to 1,
                    )
                ),
                arrayOf(
                    "CompanionObjectClass.kt",
                    mapOf(
                        "CLASS" to 2,
                        "OBJECT_DECLARATION" to 1,
                        "TYPE_PROJECTION" to 2,
                    )
                )
            )
        }
    }

    @Test
    fun testKtTreeStatsCollector() {
        setUpFixture()
        setUpCollector()
        val file = LocalFileSystem.getInstance().findFileByPath("$testDataPath/$testClass") ?: return
        ApplicationManager.getApplication().runReadAction {
            val psiFile = myFixture.psiManager.findFile(file)
            psiFile?.accept(ktTreeStatsCollector)
        }
        cleanUpFixture()
        for ((tag, value) in statistics) {
            assertEquals(statsStorage.get(tag), value)
        }
    }
}
