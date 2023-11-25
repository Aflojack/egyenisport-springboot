<?php

class sqlService{
    private mixed $conn;
    private string $servername="localhost";
    private string $username="root";
    private string $password="1234";
    private string $database="egyenisport";

    public function __construct(){
        $this->conn=mysqli_connect($this->servername, $this->username,$this->password,$this->database,3306);
        mysqli_query($this->conn, 'SET NAMES UTF-8');
        mysqli_query($this->conn, "SET character_set_results=utf8");
        mysqli_set_charset($this->conn, 'utf8');
    }

    public function getConn(){
        if($this->isConnected()){
            return $this->conn;
        }
        return null;
    }

    public function isConnected():bool{
        return isset($this->conn) && $this->conn!==false;
    }
}

?>