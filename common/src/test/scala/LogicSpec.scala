import org.scalatest.{FlatSpec, Matchers}

class LogicSpec extends FlatSpec with Matchers {

  "The 'matchLikelihood' method" should "be 100% when all attributes match" in {
    val tabby = Kitten(1, List("male", "tabby"))
    val prefs = BuyerPreferences(List("male", "tabby"))
    val result = Logic.matchLikelihood(tabby, prefs)
    result should be >= (.9999)
  }

  "The 'matchLikelihood' method" should "be 0% when no attribute match" in {
    val tabby = Kitten(1, List("male", "tabby"))
    val prefs = BuyerPreferences(List("female", "calico"))
    val result = Logic.matchLikelihood(tabby, prefs)
    result should be <= (.001)
  }
}
