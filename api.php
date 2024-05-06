<?php
include_once ('buscar.php');
include_once ('guardar.php');
include_once ('update.php');
include_once ('delete.php');
$opc = $_SERVER['REQUEST_METHOD'];
switch ($opc) {
    case 'GET':
        Buscar::buscar();
        break;
    case 'POST':
        Guardar::guardarEstudiantes();
        break;
    case 'PUT':
        Actualizar::actualizarEstudiantes();
    case 'DELETE':
        Eliminar::eliminarEstudiantes();
    default:
        # code...
        break;
}