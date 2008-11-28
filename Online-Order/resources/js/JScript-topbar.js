 function preloadImg(src)
   {
    var img=new Image();
    img.src=src;    
   }
   preloadImg("/Online-Order/resources/images/idebar_O.gif");
   
    var displayBar=true;
    function switchBar(obj)
    {   
        if(displayBar)
        {
            parent.frame.cols="0,12,*";
            displayBar=false;
            obj.src="/Online-Order/resources/images/Sidebar_O.gif";
            obj.title="open left menu";
         }
          else
          {
            parent.frame.cols="225,12,*";
            displayBar=true;
            obj.src="/Online-Order/resources/images/Sidebar_C.gif";
            obj.title="close left menu";
          }
   }