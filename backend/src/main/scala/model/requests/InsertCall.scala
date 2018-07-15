package model.requests

/**
  * A case class to make json body requests more structured.
  * @param source local URI to a file you want to access
  * @param accessType how to handle the specified file:
  *                   1 - Single file
  *                   2 - Single directory
  *                   3 - Recursive directory
  */
case class InsertCall(source: String, accessType: Int)
