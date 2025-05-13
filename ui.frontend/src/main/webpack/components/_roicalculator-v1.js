(function() { 
  handleroi1Click = () => {
    var employees = document.getElementById("employees").value;
  var revenue = document.getElementById("revenue").value;
  var ttr = document.getElementById("ttr").value;
  var tickets = document.getElementById("tickets").value;
  var cpt = document.getElementById("cpt").value;
    var resolved = tickets * .2;
    var accelerated = tickets * .15;
    var productive = 0;
    var wait = 0;
    if (employees != null) {
        productive = 0.05 * (resolved * ttr + accelerated * ttr * 0.2) / employees;
        wait = (resolved * ttr + accelerated * ttr * 0.2) / employees;
      document.getElementById("form").style.display ="none";
      document.getElementById("form-results").style.display ="block";
    }
  
  document.getElementById("result-tickets").innerHTML = resolved;
  document.getElementById("result-accelerated").innerHTML = accelerated;
  document.getElementById("result-productive").innerHTML = productive;
  document.getElementById("result-wait-hrs").innerHTML = wait;
  }
  
  const roi1Submit = document.getElementById("roi1-submit-button");
  
  if (roi1Submit != null) {
  	roi1Submit.addEventListener("click", handleroi1Click);
  }
  
}());