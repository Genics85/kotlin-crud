package com.genics85

import com.genics85.controllers.ArticleControllerImplTest
import com.genics85.dao.DAOFacadeImplTest
import org.junit.platform.suite.api.IncludeClassNamePatterns
import org.junit.platform.suite.api.SelectClasses
import org.junit.platform.suite.api.SelectPackages
import org.junit.platform.suite.api.Suite
import org.junit.runner.RunWith

@Suite
@SelectClasses(ArticleControllerImplTest::class, DAOFacadeImplTest::class)
//@SelectPackages("com.genics85.controllers","com.genics85.dao")
//@IncludeClassNamePatterns(".*Tests")
class TestRunner {
}