export class Category {

  id: string;
  name: string;
  patterns: string[] = [];

  constructor(values: Object = {}) {
    // Constructor initialization
    Object.assign(this, values);
  }

}
