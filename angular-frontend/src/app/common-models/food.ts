export class Food {

  id: number;
  type: string;
  description: string;

  constructor(id?: number, type?: string, description?: string) {
    this.id = id;
    this.type = type;
    this.description = description;
  }


}
