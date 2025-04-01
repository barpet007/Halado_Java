provider "azurerm" {
  features {}
  subscription_id = "d6971119-76ac-45b7-a27c-7e15dfc13c8d"
}

terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "4.25.0"
    }
  }
}

resource "azurerm_resource_group" "rg" {
  name     = "myFirstResourceGroup"
  location = "westeurope"
}