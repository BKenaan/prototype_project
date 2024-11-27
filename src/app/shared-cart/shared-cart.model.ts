export interface SharedCart {
  cartId: string;           
  hostId: string;           
  pin: string;              
  participants: string[];   
  items: CartItem[];        
}

export interface CartItem {
  productId: string;        
  name: string;             
  addedBy: string;          
  quantity: number;         
  price: number;            
}
