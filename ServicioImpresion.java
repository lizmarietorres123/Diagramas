import java.util.Queue;
import java.util.Scanner;

public class ServicioImpresion {

    public class PrintJob{
        private String user;
        private int pages;
        private char priotrity;

        PrintJob(String user, int pages, char priotrity){
            this.user = user;
            this.pages = pages;
            this.priotrity = priotrity;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public char getPriotrity() {
            return priotrity;
        }

        public void setPriotrity(char priotrity) {
            this.priotrity = priotrity;
        }

        public void mostrarJob(){
            System.out.println(user+" "+pages+" "+priotrity);
        }
    }



    public class Nodo{
        PrintJob dato;
        Nodo siguiente;

        Nodo(PrintJob dato){
            this.dato = dato;
            siguiente = null;
        }
    }

    public class ListaEnlazada{

        Nodo cabeza;
        Nodo cola;
        int size = 0;

        public void insertar(PrintJob dato){
            if(cabeza == null){
                cabeza = new Nodo(dato);
                cola = cabeza;
                size++;

                return;
            }

            cola.siguiente = new Nodo(dato);
            cola = cola.siguiente;
            size++;
        }

        public void eliminar(){
            if(cabeza == null){
                System.out.println("No ha agregado trabajos.");
                return;
            }

            System.out.print("Procesado: ");
            cabeza.dato.mostrarJob();
            cabeza = cabeza.siguiente;
            size--;
        }

        public void eliminarTodo(){
            int sizeIni = size;

            for (int i = 0; i < sizeIni; i++) {
                eliminar();
            }
        }

        public boolean isEmpty(){
            if(size == 0)
                return true;

            return false;
        }

        public void mostrarLista(){
            System.out.println("Size: " + size);
            Nodo actual = cabeza;

            for (int i = 0; i < size; i++) {
                actual.dato.mostrarJob();
                actual = actual.siguiente;
            }
        }
    }

    public class PrintQueue{
        ListaEnlazada alta = new ListaEnlazada();
        ListaEnlazada media = new ListaEnlazada();
        ListaEnlazada baja = new ListaEnlazada();


        public void enqueue(PrintJob dato){
            switch (dato.priotrity){
                case 'H':
                    alta.insertar(dato);
                    break;
                case 'M':
                    media.insertar(dato);
                    break;
                case 'L':
                    baja.insertar(dato);
                    break;
            }

        }

        public void dequeue(){
            if(!alta.isEmpty())
                alta.eliminar();

            else if(!media.isEmpty())
                media.eliminar();

            else if(!baja.isEmpty())
                baja.eliminar();

            else
                System.out.println("No hay elementos para procesar.");
        }

        public void dequeueAll(){
            alta.eliminarTodo();
            media.eliminarTodo();
            baja.eliminarTodo();
        }

        public void mostrarListas(){
            alta.mostrarLista();
            media.mostrarLista();
            baja.mostrarLista();
        }

    }

    public class PrintService{
        PrintQueue cola = new PrintQueue();

        public void submitJob(PrintJob job){
            cola.enqueue(job);
        }

        public void processNext(){
            cola.dequeue();
        }

        public void processAll(){
            cola.dequeueAll();
        }

        public void mostrarAll(){
            cola.mostrarListas();
        }


    }

    public class PrintManager{
        PrintService servicio = new PrintService();


    }
    public void main(String[] args){
        PrintService servicio = new PrintService();

        String user;
        int pages;
        char priority;

        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {

                user = scan.next();
                pages = scan.nextInt();
                priority = scan.next().charAt(0);
            servicio.submitJob(new PrintJob(user,pages,priority));
        }

        servicio.processAll();
    }

}
