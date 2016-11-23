/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//this function can remove a array element.
Array.remove = function(array, from, to) {
   var rest = array.slice((to || from) + 1 || array.length);
   array.length = from < 0 ? array.length + from : from;
   return array.push.apply(array, rest);
};

//this variable represents the total number of popups can be displayed according to the viewport width
var total_popups = 0;

//arrays of popups ids
var popups = [];

//this is used to close a popup
function close_popup(id)
{
   for(var i = 0; i < popups.length; i++)
   {
       if(id === popups[i])
       {
           Array.remove(popups, i);                        
           document.getElementById(id).style.display = "none";
           calculate_popups();
           return;
       }
   }   
}


function sendtext()
{
   var text = document.getElementById('textinput');
   if(text.value!=='')
   {                    
       addChat(text.value);
       text.value='';
   }                
}


function addChat(text)
{
   var input = document.getElementById('popup-messages');
   var p = document.createElement("div");
   var send = document.createElement("send");
   send.innerHTML = text;

   p.appendChild(send);                
   input.appendChild(p);
   input.scrollTop = 5000;
}

function display_popups()
{
   var right = 220;                
   var i = 0;
   for(i; i < total_popups; i++)
   {
       if(popups[i] != undefined)
       {
           var element = document.getElementById(popups[i]);
           element.style.right = right + "px";
           right = right + 320;
           element.style.display = "block";
       }
   }

   for(var j = i; j < popups.length; j++)
   {
       var element = document.getElementById(popups[j]);
       element.style.display = "none";
   }
}

function register_popup(id, name)
{

   for(var i = 0; i < popups.length; i++)
   {   
       if(id === popups[i])
       {
           Array.remove(popups, i);                    
           popups.unshift(id);                        
           calculate_popups();                                                
           return;
       }
   }               

   var 
   element = '<div class="popup-box chat-popup" id="'+ id +'">';
   //head
   element = element + '<div class="popup-head">';
   element = element + '<div class="popup-head-left">'+ name +'</div>';
   element = element + '<div class="popup-head-right"><a href="javascript:close_popup(\''+ id +'\');">&#10005;</a></div></div><br><hr>';
   //massage
   element = element + '<div class="popup-messages"></div>';
   //bottom
   element = element + '<div class="popup-bottom">';
   element = element + '<div class="popup-bottom-right"><button onclick="sendtext()">Kirim</button> </div>';
   element = element + '<div class="popup-bottom-left"><input id="textinput" type="text"> </div></div></div>';                

   document.getElementsByTagName("body")[0].innerHTML = document.getElementsByTagName("body")[0].innerHTML + element;  
   popups.unshift(id);
   calculate_popups();
}

//calculate the total number of popups suitable and then populate the toatal_popups variable.
function calculate_popups()
{
   var width = window.innerWidth;
   if(width < 540)
   {
       total_popups = 0;
   }
   else
   {
       width = width - 200;
       //320 is width of a single popup box
       total_popups = parseInt(width/320);
   }

   display_popups();

}

//recalculate when window is loaded and also when window is resized.
window.addEventListener("resize", calculate_popups);
window.addEventListener("load", calculate_popups);