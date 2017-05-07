<?php

       $area=$_POST["location"];
        $date=$_POST["dateAndTime"];
        $size=$_POST["size"];
        $color=$_POST["color"];


            						$conn=new mysqli("localhost","root","","bpp");
            
            							if ($conn->connect_error) {
            									die("Connection failed: " . $conn->connect_error);
            											} 
            								//echo "Connected successfully";

 $sql="select * from birdspecies NATURAL JOIN location NATURAL JOIN taxonomy where location.area='".$area."';";
//echo $sql;
$result=$conn->query($sql);

   if($result->num_rows>0)
            {
            
            while($row=$result->fetch_assoc())     {  

            			echo $row["primarybirdname"].";	".strtolower($row["description"])."\n";

            }

        }
        else
        {

        	echo "no birds ";
        }


?>