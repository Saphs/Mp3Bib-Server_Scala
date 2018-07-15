package test_implementations

import input_output.BasicDAO

class BasicDAOTestImpl extends BasicDAO{
  override val defaultPath: String = s"${Constants.resourcePath}directory_test_structure"
}
