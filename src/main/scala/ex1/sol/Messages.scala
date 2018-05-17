package ex1.sol

/**
  * Simple message with a printable content
  * @param content the content of the message
  */
case class ContentMessage(content: String) {

}

/**
  * Simple message that indicates an acknowledgment
  */
case class AckMessage() {

}

/**
  * Simple message that indicates that the maximum number of prints has been reached
  */
case class OutMessage() {

}
