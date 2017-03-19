// Code borrowed from DB assignment last year: https://github.com/Josh2609/Carkea/blob/master/user/js/addToWishlist.js
function addToBookmarks(quizID, studentID) {
  if (window.XMLHttpRequest) {
    // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  } else { // code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function() {
    if (this.readyState==4 && this.status==200) {
      document.getElementById("bookmarkResult").innerHTML=this.responseText;
    }
  }
  document.getElementById("bookmark").textContent = "Remove Bookmark"; 
  document.getElementById("bookmark").onclick = function() { removeBookmark(quizID, studentID); }
  xmlhttp.open("GET","../AddBookmark?quizID="+quizID+"&studentID="+studentID,true);                      
  xmlhttp.send();
}

function removeBookmark(quizID, studentID) {
  if (window.XMLHttpRequest) {
    // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp=new XMLHttpRequest();
  } else { // code for IE6, IE5
    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange=function() {
    if (this.readyState==4 && this.status==200) {
      document.getElementById("bookmarkResult").innerHTML=this.responseText;
    }
  }
  document.getElementById("bookmark").textContent = "Bookmark Quiz"; 
  document.getElementById("bookmark").onclick = function() { addToBookmarks(quizID, studentID); }
  xmlhttp.open("GET","../RemoveBookmark?quizID="+quizID+"&studentID="+studentID,true);                      
  xmlhttp.send();
}