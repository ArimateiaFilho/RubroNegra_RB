/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rb;

/**
 *
 * @author filho
 */
public class RB {
    private No ext=new No();
    private int cont=0;
    private No raiz=ext;
    private boolean resultado=true;
    private No iniciarNo(double valor){
        No pt=new No();
        pt.setValor(valor);
        return pt;
    }
    public void exter(){
        System.out.println(ext.getCor());
    }
    public void add(double valor){
        add(iniciarNo(valor));
        if(resultado){
            cont++;
        }
    }
    private void add(No z){
        No y=ext;
        No x=raiz;
        while(x!=ext){
            y=x;
            if(x.getValor()==z.getValor()){
                System.out.println("numero ja existe");
                y=null;
                x=ext;
                resultado=false;
            }else{
                resultado=true;
                if(x.getValor()>z.getValor()){
                    x=x.getEsq();
                }else{
                    x=x.getDir();
                }
            }
        }
        if(y!=null){
            if(y==ext){
                raiz=z;
                z.setPai(ext);
                z.setCor('N');
            }else{
                if(y.getValor()<z.getValor()){
                    y.setDir(z);
                }else{
                    y.setEsq(z);
                }
                z.setPai(y);
                z.setCor('R');
            }
            z.setDir(ext);
            z.setEsq(ext);
            rota(z);
        }
        
    }
    
    private void rota(No z){
        while(z.getPai().getCor()=='R'){
            if(z.getPai().getPai().getEsq()==z.getPai()){
                No tio=z.getPai().getPai().getDir();
                if(tio.getCor()=='R'){
                    z.getPai().setCor('N');
                    tio.setCor('N');
                    z.getPai().getPai().setCor('R');
                    z=z.getPai().getPai();
                }else{
                    if(z.getPai().getDir()==z){
                        z=z.getPai();
                        RE(z);
                        System.out.print("RE  ");
                    }
                    z.getPai().setCor('N');
                    z.getPai().getPai().setCor('R');
                    RD(z.getPai().getPai());
                    System.out.println("RD");
                    
                    
                }
            }else{
                No tio=z.getPai().getPai().getEsq();
                if(tio.getCor()=='R'){
                    z.getPai().setCor('N');
                    tio.setCor('N');
                    z.getPai().getPai().setCor('R');
                    z=z.getPai().getPai();
                }else{
                    if(z.getPai().getEsq()==z){
                        z=z.getPai();
                        RD(z);
                        System.out.print("RD  ");
                    }
                    z.getPai().setCor('N');
                    z.getPai().getPai().setCor('R');
                    RE(z.getPai().getPai());
                    System.out.println("RE");                    
                }
            }
        }
        raiz.setCor('N');
        
        
    }
    
    private void moverPai(No pt,No ptu){
        if(pt.getPai()==ext){
            raiz=ptu;
        }else{
            if(pt.getPai().getEsq()==pt){
                pt.getPai().setEsq(ptu);
            }else{
                pt.getPai().setDir(ptu);
            }
        }
        ptu.setPai(pt.getPai());
        
    }
    
    private void RD(No ptu){
        No aux=ptu.getEsq();
        ptu.setEsq(aux.getDir());
        aux.setDir(ptu);
        if(ptu.getPai()==ext){
            raiz=aux;
            aux.setPai(ext);
        }else{
            if(ptu==ptu.getPai().getEsq()){
                ptu.getPai().setEsq(aux);
                aux.setPai(ptu.getPai());
            }else{
                ptu.getPai().setDir(aux);
                aux.setPai(ptu.getPai());
            }
        }
        if(ptu.getEsq()!=ext){
            ptu.getEsq().setPai(ptu);
        }
        ptu.setPai(aux);
        
    }
    
    private void RE(No ptu){
        No aux=ptu.getDir();
        ptu.setDir(aux.getEsq());
        aux.setEsq(ptu);
        if(ptu.getPai()==ext){
            raiz=aux;
            aux.setPai(ext);
        }else{
            if(ptu==ptu.getPai().getEsq()){
                ptu.getPai().setEsq(aux);
                aux.setPai(ptu.getPai());
            }else{
                ptu.getPai().setDir(aux);
                aux.setPai(ptu.getPai());
            }
        }
        if(ptu.getDir()!=ext){
            ptu.getDir().setPai(ptu);
        }
        ptu.setPai(aux);
        
    }
    
    public void preOrdem(){
        preOrdem(raiz);
    }
    
    private void preOrdem(No n){
        if(n!=ext){
            if(n.getPai()==ext){
                System.out.print("Pai: Raiz---");
            }else{
                System.out.print("Pai: "+n.getPai().getValor()+"---");
            }
            System.out.print(" No: "+n.getValor()+"---");
            System.out.println(" Cor: "+n.getCor());
            if(n.getEsq()!=ext){
                preOrdem(n.getEsq());
            }
            if(n.getDir()!=ext){
                preOrdem(n.getDir());
            }
        }
    }
    public RB(){
        ext.setCor('N');
        ext.setDir(ext);
        ext.setEsq(ext);
        ext.setPai(ext);
    }
    
    private No sucessor(No s){
        s=s.getDir();
        while(s.getEsq()!=ext){
            s=s.getEsq();
        }
        return s;
    }
    
    private void remover(No z){
        char Cor=z.getCor();
        No x;
        if(z.getEsq()==ext){
            x=z.getDir();
            moverPai(z,x);
        }else{
            if(z.getDir()==ext){
                x=z.getEsq();
                moverPai(z,x);
            }else{
                No s=sucessor(z);
                x=s.getDir();
                ext.setPai(s);
                Cor=s.getCor();
                if(s!=z.getDir()){
                    moverPai(s,x);
                    s.setDir(z.getDir());
                    s.getDir().setPai(s);
                }
                moverPai(z,s);
                s.setEsq(z.getEsq());
                s.getEsq().setPai(s);
                s.setCor(z.getCor());
                /*if(Cor=='N'&&x==ext&&s==z.getDir()){
                    if(s.getEsq().getEsq()==ext){
                        /*if(s.getEsq().getDir().getCor()=='N'){
                           s.setCor('N');
                        }else{
                            s.setCor('R');
                        }
                        RE(s.getEsq());
                        RD(s);
                    }else{
                        RD(s);
                    }
                    s.setCor('N');
                    s.getPai().setCor('R');
                    s.getPai().getEsq().setCor('N');
                }*/
            }
        }
        if(Cor=='N'){
            rotaCorRB(x);
        }
        ext.setPai(ext);
        ext.setCor('N');
    }
    
    private void rotaCorRB(No x){
        while(x!=raiz&&x.getCor()!='R'){
            if(x==x.getPai().getEsq()){
                No w=x.getPai().getDir();
                if(w.getCor()=='R'){
                    // caso 1
                    w.setCor('N');
                    w.getPai().setCor('R');
                    RE(x.getPai());
                    w=x.getPai().getDir();
                }else{
                    if(w.getEsq().getCor()=='N'&&w.getDir().getCor()=='N'){
                        // caso 2
                        w.setCor('R');
                        x=x.getPai();
                    }else{
                        if(w.getDir().getCor()=='N'){
                           // caso 3
                           w.getEsq().setCor('N');
                           w.setCor('R');
                           RD(w);
                           w=w.getPai();
                        }
                        //caso 4
                        w.setCor(x.getPai().getCor());
                        w.getDir().setCor('N');
                        x.getPai().setCor('N');
                        RE(x.getPai());
                        x=raiz;
                    }
                }
            }else{
                No w=x.getPai().getEsq();
                if(w.getCor()=='R'){
                    // caso 1
                    w.setCor('N');
                    w.getPai().setCor('R');
                    RD(x.getPai());
                    w=x.getPai().getEsq();
                }else{
                    if(w.getEsq().getCor()=='N'&&w.getDir().getCor()=='N'){
                        // caso 2
                        w.setCor('R');
                        x=x.getPai();
                    }else{
                        if(w.getEsq().getCor()=='N'){
                           // caso 3
                           w.getDir().setCor('N');
                           w.setCor('R');
                           RE(w);
                           w=w.getPai();
                        }
                        //caso 4
                        w.setCor(x.getPai().getCor());
                        w.getEsq().setCor('N');
                        x.getPai().setCor('N');
                        RD(x.getPai());
                        x=raiz;
                    }
                }
            }
        }
        x.setCor('N');
    }
    private No buscar(double valor){
        No aux=raiz;
        while(aux!=ext){
            if(aux.getValor()==valor){
                break;
            }else{
                if(valor<aux.getValor()){
                    aux=aux.getEsq();
                }else{
                    aux=aux.getDir();
                }
            }
        }
        return aux;
    }
    public void remover(double valor){
        No aux=buscar(valor);
        remover(aux);
        cont--;
    }
}
