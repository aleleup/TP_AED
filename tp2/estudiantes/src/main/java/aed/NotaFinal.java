package aed;

public class NotaFinal implements Comparable<NotaFinal> {
    public double _nota;
    public int _id;

    public NotaFinal(double nota, int id){
        _nota = nota;
        _id = id;
    }

  @Override
  public boolean equals(Object otro) {
        if (otro != null && otro.getClass() == this.getClass() ){
            NotaFinal otraNotaFinal = (NotaFinal) otro;
            return otraNotaFinal._nota == this._nota && otraNotaFinal._id == _id;
        }
        return false;
    }
    
    @Override
    public int compareTo(NotaFinal otra){
        
        if (this._nota != otra._nota){
            return Double.compare(this._nota, otra._nota);
        }
        return this._id - otra._id;
    }
}
