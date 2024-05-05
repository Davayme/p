<?php

class Conexion{

    public static function conectar(){

        define('server', 'localhost');
        define('db', 'quinto');
        define('user', 'root');
        define('psw', '');
        define('port', '3306');

        $opc = array(PDO::MYSQL_ATTR_INIT_COMMAND => 'SET NAMES utf8');

        try {
            $cn = new PDO("mysql:sever=".server.";dbname=".db.";port".port, user,psw,$opc);
            return $cn;
        } catch (Exception $th) {
            echo $th;
        }

    }
}